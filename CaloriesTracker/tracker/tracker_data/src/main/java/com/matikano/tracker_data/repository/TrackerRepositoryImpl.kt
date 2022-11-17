package com.matikano.tracker_data.repository

import com.matikano.core.domain.model.Nutrients
import com.matikano.tracker_data.local.TrackerDao
import com.matikano.tracker_data.mapper.toTrackableFood
import com.matikano.tracker_data.mapper.toTrackedFood
import com.matikano.tracker_data.mapper.toTrackedFoodEntity
import com.matikano.tracker_data.remote.OpenFoodApi
import com.matikano.tracker_domain.model.TrackableFood
import com.matikano.tracker_domain.model.TrackedFood
import com.matikano.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrackerRepositoryImpl @Inject constructor(
    private val dao: TrackerDao,
    private val api: OpenFoodApi
): TrackerRepository {

    override suspend fun searchFoods(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> = try {
        val searchDto = api.searchFood(
            query = query,
            page = page,
            pageSize = pageSize
        )
        Result.success(
            searchDto.products
                .filter {
                    val calories =
                        it.nutriments.carbohydrates100g * Nutrients.CARBS.kcalPerGramRatio +
                        it.nutriments.proteins100g * Nutrients.PROTEIN.kcalPerGramRatio +
                        it.nutriments.fat100g * Nutrients.FAT.kcalPerGramRatio
                    val lowerBound = calories * 0.99f
                    val upperBound = calories * 1.01f
                    it.nutriments.energyKcal100g in (lowerBound..upperBound)
                }
                .mapNotNull { product ->
                    product.toTrackableFood()
                }
        )
    } catch (e: Exception){
        e.printStackTrace()
        Result.failure(e)
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        dao.insertTrackedFood(food.toTrackedFoodEntity())
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        dao.deleteTrackedFood(food.toTrackedFoodEntity())
    }

    override fun getFoodsForDate(date: LocalDate): Flow<List<TrackedFood>> {
        return dao.getFoodsForDate(
            day = date.dayOfMonth,
            month = date.monthValue,
            year = date.year
        ).map { entities ->
            entities.map { entity ->
                entity.toTrackedFood()
            }
        }
    }

}
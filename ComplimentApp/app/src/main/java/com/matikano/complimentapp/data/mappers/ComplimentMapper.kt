package com.matikano.complimentapp.data.mappers

import com.matikano.complimentapp.data.remote.ComplimentApi
import com.matikano.complimentapp.data.remote.ComplimentDto
import com.matikano.complimentapp.domain.compliment.Compliment

fun ComplimentDto.toCompliment(): Compliment =
    Compliment(content = this.compliment)
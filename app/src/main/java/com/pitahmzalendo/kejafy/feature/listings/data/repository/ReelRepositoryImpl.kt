package com.pitahmzalendo.kejafy.feature.listings.data.repository

import com.pitahmzalendo.kejafy.feature.listings.data.mapper.toDomain
import com.pitahmzalendo.kejafy.feature.listings.data.remote.ReelApi
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PagedResult
import com.pitahmzalendo.kejafy.feature.listings.domain.model.PropertyReel
import com.pitahmzalendo.kejafy.feature.listings.domain.repository.ReelRepository

class ReelRepositoryImpl(
    private val api: ReelApi
) : ReelRepository {

    override suspend fun getFeed(cursor: String?): Result<PagedResult<PropertyReel>> {
        return runCatching {
            api.getFeed(cursor).toDomain { dto ->
                PropertyReel(
                    id = dto.id,
                    propertyId = 0, // In backend ListingVideo doesn't directly have propertyId in response, but it's linked
                    videoUrl = dto.videoUrl,
                    thumbnailUrl = dto.thumbnailUrl,
                    durationSeconds = dto.durationSeconds ?: 0
                )
            }
        }
    }
}

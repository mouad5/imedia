package de.imedia24.shop.db.repository

import de.imedia24.shop.db.entity.ProductEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : CrudRepository<ProductEntity, String> {

    fun findBySku(sku: String): ProductEntity?

     fun save(product: ProductEntity): ProductEntity?

    @Query( "select o from ProductEntity o where o.sku in :skus" )
    fun findBySkus(skus: List<String>):List<ProductEntity>
}
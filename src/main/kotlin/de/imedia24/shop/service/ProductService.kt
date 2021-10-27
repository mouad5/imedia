package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.entity.ProductEntity.Companion.toProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {


    fun findProductBySku(sku: String): ProductResponse? {
       var productEntity = productRepository.findBySku(sku)

        return productEntity?.toProductResponse()

    }
    fun findProductsBySkus(skus: List<String>): List<ProductResponse>? {
        var listProductEntity = productRepository.findBySkus(skus)
        var listProductsResponse:ArrayList<ProductResponse> = ArrayList()

        for (p: ProductEntity in listProductEntity)
        {
            listProductsResponse.add(p.toProductResponse())
        }
        return listProductsResponse

    }
    fun saveProduct(productRequest: ProductRequest): ProductResponse? {
          val product=productRepository.save(productRequest.toProductEntity())
         return product?.toProductResponse()
    }



}

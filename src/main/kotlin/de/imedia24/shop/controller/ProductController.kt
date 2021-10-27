package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/products/{sku}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }
    @GetMapping("/products/{skus}", produces = ["application/json;charset=utf-8"])
    fun findProductsBySkus(
            @PathVariable("skus") skus:  List<String>
    ): ResponseEntity<List<ProductResponse>> {
        logger.info("Request for product $skus")

        val products: List<ProductResponse> ? = productService.findProductsBySkus(skus)
        return if(products?.isEmpty() == true) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.status(HttpStatus.OK)
                    .body(products)
        }
    }
    @PostMapping("/product")
    fun saveProduct(@RequestBody product: ProductRequest): ResponseEntity<ProductResponse> {
        logger.info("Request for product save")
        val productResponse=productService.saveProduct(product)

        return if(productResponse == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } else {
            ResponseEntity.ok(productResponse)
        }

    }
    @PutMapping("/product")
    fun editProduct(@RequestBody product: ProductRequest): ResponseEntity<ProductResponse> {
        val productResponse=productService.saveProduct(product)
        logger.info("Request for product update")

        return if(productResponse == null) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } else {
            ResponseEntity.ok(productResponse)
        }

    }



}

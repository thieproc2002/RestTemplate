@PostMapping("/product")
public Product addProduct(@RequestBody Product product) {
productService.addProducts(product);
return product;
}
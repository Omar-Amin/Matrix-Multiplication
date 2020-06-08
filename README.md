# Matrix Multiplication

This project implements different kind of matrix multiplication, which is also known methods regarding optimizing cache performance. A normal, transposed, tiled transposed and threaded version of matrix multiplcation have been implemented, if you are interested you can check out the implementation in the folder. The runtime performance for the different implementations has also been measured which can be seen down below, but if you are interested, there is a main class where you can make your own test by changing the matrices.

### Performance

- Normal: 73065 ms
- Transposed: 11380 ms
- Tiled: 21516 ms
- Transposed tiled: 12289 ms
- Threaded (transposed tiled): 3325 ms

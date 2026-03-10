**Refleksi terhadap kegunaan TDD (Percival, 2017)**

Menurut prinsip yang dijelaskan oleh Percival, kegunaan dari alur TDD dapat dilihat dari apakah TDD tersebut dapat berfungsi sebagai safety net dan juga sebagai alat untuk membantu desain sistem, bukan hanya sekadar tugas tambahan saat coding.

Dalam praktiknya, alur TDD ini cukup membantu karena memaksa kita untuk mendefinisikan aturan dari fitur seperti Voucher dan COD sebelum menulis logika utama dari program. Dengan adanya test seperti testAddPaymentVoucherSuccess dan testAddPaymentCODRejectedEmptyAddress, kita tidak perlu menebak apakah implementasi yang dibuat sudah benar atau belum, karena hasil test yang pass langsung memberikan konfirmasi bahwa kode sudah bekerja sesuai dengan yang diharapkan.

Selain itu, menulis test terlebih dahulu juga berfungsi seperti spesifikasi fungsional. Dari test tersebut menjadi jelas bahwa objek Payment harus memiliki relasi dengan Order, dan bahwa paymentData berbentuk Map harus memiliki key tertentu seperti voucherCode.

Test juga sangat membantu saat melakukan tahap refactor. Ketika validasi dipindahkan ke method private seperti validateVoucher(), test yang sudah ada memastikan bahwa perubahan tersebut tidak merusak aturan yang sudah dibuat sebelumnya, misalnya aturan bahwa voucher harus terdiri dari 8 digit.

Hal yang bisa diperbaiki ke depannya adalah dari sisi granularitas test. Pada percobaan ini, beberapa test masih mencakup satu fungsi secara keseluruhan. Ke depannya, test bisa dibuat lebih kecil dan spesifik, misalnya membuat test terpisah untuk mengecek panjang voucher saja, lalu test lain untuk mengecek prefix voucher. Dengan begitu tahap Red dalam TDD bisa menjadi lebih jelas dan spesifik.


---
**Refleksi terhadap prinsip F.I.R.S.T.**

Berdasarkan evaluasi terhadap PaymentServiceTest, sebagian besar test yang dibuat sudah mengikuti prinsip F.I.R.S.T.:

Fast
Test berjalan dengan cepat karena menggunakan @Mock untuk PaymentRepository, sehingga tidak perlu melakukan operasi database yang lambat. Test dapat dijalankan hanya dalam waktu milidetik.

Independent
Setiap test bersifat independen karena menggunakan method @BeforeEach untuk membuat objek Order dan PaymentService yang baru. Dengan demikian, tidak ada test yang bergantung pada hasil dari test sebelumnya.

Repeatable
Test dapat dijalankan berulang kali dengan hasil yang sama karena tidak bergantung pada faktor eksternal seperti waktu sistem atau koneksi jaringan.

Self-Validating
Test menggunakan assertion seperti assertEquals dan assertNull, sehingga hasil test langsung berupa pass atau fail. Tidak diperlukan pengecekan log secara manual untuk mengetahui hasilnya.

Timely
Test ditulis sebelum implementasi kode, sesuai dengan fase RED dalam TDD. Hal ini memastikan bahwa kode yang dibuat sejak awal sudah dirancang agar mudah untuk diuji.

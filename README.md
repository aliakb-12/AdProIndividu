title: eshop-individu
emoji: 🛒
colorFrom: blue
colorTo: indigo
sdk: docker
pinned: false

****TUTORIAL 1****

***REFLECTION 1***
- 
- **Clean code**: Membuat setiap file melakukan hal yang tersendiri. Seperti Product model menghanlde struktur data, ProductRepository menghandle data-datanya, dll. Serta juga menggunakan nama-nama yang jelas untuk menamakan function seperti findByID, findAll, dll.
  
- **Secure coding and mistakes from source code**: Ketika membuat product pertama kali kita membuat ID dengan String. Tetapi saya merasa bahwa ini bukanlah hal yang bagus untuk diimplementasi dengan alasan security dan kesimpelan kode kita. Masalah keamanan utama yang muncul adalah kerentanan terhadap serangan enumerasi, di mana pengguna lain dapat dengan mudah menebak-nebak ID dari produk yang ada dalam sistem. Contohnya, rencana awal saya adalah membuat ID produk mengikuti pola sequential seperti "ProductId1", "ProductId2", "ProductId3", dan seterusnya. Pola seperti ini jelas sangat mudah ditebak oleh pengguna lainnya. Mereka hanya harus mencoba-coba membuka url seperti /product/edit/ProductId1, /product/edit/ProductId2, dan seterusnya untuk mengakses produk yang mungkin bukan miliknya. Untuk mengatasi masalah ini, saya mengganti id dari String menjadi UUID yang akan meningkatkan keamanan dan menyederhanakan kode karena generasi ID dapat dilakukan secara otomatis menggunakan UUID.randomUUID() tanpa perlu logic tambahan.


***REFLECTION 2***
-
- **How many unit tests should be made in a class?** Tidak ada minimal dan maksimal untuk berapa unit test yang harus dibikin dalam 1 _class_. Dikarenakan unit-test bukanlah tentang seberapa banyak test yang kita bikin. Melainkan tentang seberapa banayk program yang kita cover dengan test kita ini.
- **How to make sure that our unit tests are enough to verify our program?** kita bisa melakukan coverage tests, dengan sebisa mungkin mendekati 100%. Dan juga kita bisa mencoba manual dengan mencoba membuka program yang kita bikin dan mencoba semua edge case yang bisa kita pikirkan. Selain itu kita juga bisa meminta user lain untuk mencoba program kita dikarekanakan terkadang perspektif kita sendiri akan berbeda dengan yang laiinnya. 
- **If you have 100% code coverage, does that mean your code has no bugs or errors?** Tidak, 100% code coverage hanya merupakan indikator bahwa semua baris kode telah dieksekusi oleh test, bukan jaminan bahwa kode tersebut bebas dari bug atau error. Code coverage mengukur seberapa banyak kode yang "tersentuh" oleh test, tetapi tidak mengukur apakah logika di dalam kode tersebut bekerja dengan benar. Selain itu juga, 100% code coverage tidak menjamin bahwa semua skenario edge case telah diuji, seperti input null, nilai negatif, atau kondisi boundary lainnya. Kualitas test jauh lebih penting daripada kuantitas coverage test.


--------
****TUTORIAL 2****

***REFLECTION***
- **Code quality issue** = Beberapa isu kualitas kode terdeteksi oleh alat PMD. Isu pertama berkaitan dengan penamaan variabel yang terlalu pendek, di mana variabel yang sebelumnya hanya bernama id diubah menjadi productID agar lebih deskriptif. Selain itu, ditemukan variabel lokal yang seharusnya dapat dideklarasikan sebagai final. Perbaikan dilakukan dengan menyesuaikan deklarasi variabel sesuai rekomendasi alat analisis kode, tanpa mengubah perilaku atau fungsionalitas program. Isu terakhir berkaitan dengan kelengkapan dan kualitas unit test, di mana beberapa pengujian belum sepenuhnya mencerminkan skenario penggunaan yang realistis. Untuk mengatasinya, dilakukan peninjauan ulang terhadap logika pengujian dan dipastikan bahwa setiap unit test benar-benar memverifikasi perilaku metode yang diuji.
-  **CI/CI Reflection** = Berdasarkan workflow CI/CD yang telah diimplementasikan, dapat disimpulkan bahwa sistem tersebut telah memenuhi definisi Continuous Integration dan Continuous Deployment. Setiap perubahan kode yang di-push ke repository secara otomatis memicu pipeline yang menjalankan unit test dan analisis kualitas kode, sehingga proses integrasi kode dilakukan secara berkelanjutan. Aspek Continuous Integration terpenuhi karena pipeline secara otomatis menjalankan pengujian dan analisis kualitas kode setiap kali ada perubahan, sehingga kesalahan dapat terdeteksi lebih awal. Sementara itu, aspek Continuous Deployment terpenuhi karena aplikasi dapat dideploy ke PaaS secara otomatis setelah seluruh proses CI berhasil, meskipun pipeline masih dapat dikembangkan lebih lanjut agar lebih mendekati praktik industri.

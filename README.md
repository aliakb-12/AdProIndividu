***REFLECTION 1***
- 
- **Clean code**: Membuat setiap file melakukan hal yang tersendiri. Seperti Product model menghanlde struktur data, ProductRepository menghandle data-datanya, dll. Serta juga menggunakan nama-nama yang jelas untuk menamakan function seperti findByID, findAll, dll.
  
- **Secure coding and mistakes from source code**: Ketika membuat product pertama kali kita membuat ID dengan String. Tetapi saya merasa bahwa ini bukanlah hal yang bagus untuk diimplementasi dengan alasan security dan kesimpelan kode kita. Masalah keamanan utama yang muncul adalah kerentanan terhadap serangan enumerasi, di mana pengguna lain dapat dengan mudah menebak-nebak ID dari produk yang ada dalam sistem. Contohnya, rencana awal saya adalah membuat ID produk mengikuti pola sequential seperti "ProductId1", "ProductId2", "ProductId3", dan seterusnya. Pola seperti ini jelas sangat mudah ditebak oleh pengguna lainnya. Mereka hanya harus mencoba-coba membuka url seperti /product/edit/ProductId1, /product/edit/ProductId2, dan seterusnya untuk mengakses produk yang mungkin bukan miliknya. Untuk mengatasi masalah ini, saya mengganti id dari String menjadi UUID yang akan meningkatkan keamanan dan menyederhanakan kode karena generasi ID dapat dilakukan secara otomatis menggunakan UUID.randomUUID() tanpa perlu logic tambahan.

  

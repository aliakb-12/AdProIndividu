title: Eshop Individu
emoji: 🦀
colorFrom: indigo
colorTo: purple
sdk: docker
pinned: false

***REFLECTION 3***

---

- **Explain what principles you apply to your project**

  - **SRP** = Prinsip SRP diterapkan dengan memastikan bahwa setiap kelas hanya memiliki satu tanggung jawab utama dan satu alasan untuk berubah. `CarController` sekarang hanya bertugas menangani routing HTTP dan meneruskan request ke lapisan service. Kelas ini tidak lagi mengimplement `ProductController`, sehingga tanggung jawab pengelolaan Car dan Product tidak tercampur dalam satu controller.

  - **OCP** = OCP diterapkan dengan memperkenalkan abstraksi repository melalui `CarRepositoryInterface`. Dengan adanya interface ini, sistem menjadi terbuka untuk pengembangan (extension) dan tertutup untuk modifikasi (modification). Implementasi baru seperti `CarDatabaseRepository` dapat ditambahkan tanpa mengubah kode yang sudah ada.

  - **LSP** = Sebelumnya `CarController extends ProductController`. Pola ini diubah karena `CarController` bukan subtipe sejati dari `ProductController`. Setelah diperbaiki, `CarController` dan `ProductController` berdiri secara independen, dan setiap implementasi dari `CarService` atau `CarRepositoryInterface` dapat ditukar tanpa mempengaruhi kebenaran dan konsistensi program.

  - **ISP** = ISP sudah terimplementasi dengan baik sejak awal. `CarService` hanya mengekspos metode yang relevan dengan operasi Car yaitu `create`, `findAll`, `findById`, `update`, dan `deleteCarById`.

  - **DIP** = `CarController` sekarang bergantung pada `CarService` (interface), bukan pada `CarServiceImpl` (kelas konkret). `CarServiceImpl` sekarang bergantung pada `CarRepositoryInterface`, bukan langsung pada `CarRepository`.

- **Explain the advantages of applying SOLID principles to your project with examples.**

  - **Lebih mudah dikembangkan tanpa merusak kode yang sudah ada (OCP)** = Karena `CarServiceImpl` bergantung pada `CarRepositoryInterface`, kita bisa menggunakan `CarRepository` (in-memory) untuk testing dan `CarDatabaseRepository` untuk produksi tanpa mengubah logika service sama sekali.

  - **Substitusi dan pengujian lebih aman (LSP + DIP)** = Karena `CarController` bergantung pada interface `CarService`, saat unit testing kita bisa menyuntikkan mock service tanpa harus menjalankan repository atau database sungguhan. Controller tidak peduli apa implementasi di balik interface tersebut.

  - **Perubahan terisolasi dan risiko lebih kecil (SRP)** = Ketika aturan bisnis Car berubah, hanya `CarServiceImpl` yang perlu diubah. Ketika routing HTTP berubah, hanya `CarController` yang disentuh. Perubahan tidak merambat ke seluruh codebase.

  - **Interface yang ramping dan jelas tujuannya (ISP)** = Dengan menjaga `CarService` tetap fokus, implementasi service baru hanya perlu memikirkan operasi Car tanpa harus mengimplementasikan metode yang tidak relevan.

- **Explain the disadvantages of not applying SOLID principles to your project with examples.**

  - **Kelas yang terlalu besar sulit dikelola (Melanggar SRP)** = Jika `CarController` terus mewarisi `ProductController` dan keduanya terus berkembang, akan terbentuk kelas besar yang akan sulit untuk dikelola nantinya.

  - **Setiap perubahan berisiko merusak kode lama (Melanggar OCP)** = Tanpa `CarRepositoryInterface`, mengganti penyimpanan in-memory ke database berarti harus mengedit langsung `CarRepository` dan `CarServiceImpl`.

  - **Inheritance yang salah menyebabkan bug runtime tersembunyi (Melanggar LSP)** = `CarController extends ProductController` memungkinkan situasi di mana Spring memperlakukan `CarController` sebagai `ProductController`, sehingga request Car berpotensi diproses melalui logika Product.

  - **Ketergantungan langsung membuat kode sulit untuk diupdate (Melanggar DIP)** = Pada kode awal, `CarController` terhubung langsung ke `CarServiceImpl`. Jika ingin memperkenalkan `CarServiceV2`, controller harus dimodifikasi atau nama kelas diganti.

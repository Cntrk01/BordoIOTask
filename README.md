## İçerik
Öncelikli olarak merhaba.Bu projede Jetpack Compose ile kendi oluşturduğum Mock API'den veri çekerek kullanıcı ekranında gösterdim.Anasayfada Bordo IO web sitesinin home sayfasındaki verileri gösterdim.
Verileri çekerken loading durumu gösteriliyor.Eğer internet zaman aşımına uğrarsa,internet koparsa gibi hata durumlarını değerlendirdim kullanıcıya bilgisini vererek tekrar istek atmasını sağladığım durumlar mevcut.
Proje sayfasında ise Bordo Io'nun projelerinin bulunduğu bir sayfa oluşturdum.Burada da veriler çekiliyor.Gösterilen verinin üzerine tıklayınca karşımıza bir pop-up çıkıyor ve onun içerisinde kullanıcıya gösteriliyor.
Proje sayfasında bir de arama kısımımız var.Bu kısım API'de arama yapmıyor.API'den gelen liste içerisinde bir arama yapıyoruz.Kullanıcının aradığı kelimeleri başlık ve açıklama kısımında arıyor ve eşleşenleri ekranda gösteriyor.


## Kullanılan Teknolojiler
-Flows,
-BottomNavigation,
-SearchBar,
-Mock Api,
-Coroutines,
-Retrofit,
-Hilt,
-MVVM Architecture
-State Management,
-Navigation

## Teknoloji Açıklamaları
-State Flow kullandım.Bellek tüketimi için optimize edilmiş ve iş parçacığı günveliği sağlanmış bir yapısı olduğu için tercih ettim,
-Bottom navigation sayfalar arasında geçiş yapılması için kullandım,
-Search Bar'da liste içerisinden veri aramak için kullanılmıştır,
-Mock Api ise kendim oluşturduğum veri listesini sunucuya yükleyerek GET işlemi yapmak için oluşturuldu,
-Coroutines sunucu,veritabanı gibi işlemlerde uzun bekleme sonuçları doğurabileceği durumlarda ui tarafını bloklamaması için kullanılır ve threadlere göre işlem yaptım,
-Retrofit ile verileri API'den çektim
-Hilt ile class bağımlılıklarını yönettim,
-Mvvm mimarisi kullanarak paketleme işlemlerini,veri akışı gibi süreçleri kolay bir şekilde yönettim.Bu mimaride her sınıfın kendi amacı ve kendi bir görevi vardır.
-Recomposition = Uygulamanın görünümünün durumlara bağlı olarak yeniden çizilmesidir.Yani state bir duruma bağlı olarak bilgilendiriliyor ekran yeniden çiziliyor
-State management içerisinde recomposition durumlarını tekrar değerlendirmem gerekti.Burada search bar kısımında uygulamam gerekti çünkü yazılan yazıyı state management durumu ile yöneterek ekranda gözükmesini ve yazılan yazıyı remember ile tutarak search bara özel hafıa kazandırılmasını sağladım.
-Yine recomposition yaptığım diğer durumlar da var.Çünkü API'den gelen her farklı cevaba göre ekranda farklı sonuc göstermem gerkeiyordu.Bu durumda da Stateless yapısını kullanarak recomposition yapmış oldum,
-API'den gelen modelimi  dikkate alarak iki farklı composable fonksiyon oluşturdum.Bunları State Hoisting kuralları doğrultusunda oluşturdum.Ve gereksiz kod tanımlamalarından kurtularak bu fonksiyonları farklı composablelar içinde kullandım.(SharedTitleDescription.kt,ShowImageTitleCardView.kt shared_layout içinde)
-Keza Error Loading durumları da State Hoisting kuralları doğrultusunda yapıldı bir çok farklı fonksiyon içerisinden çağırılmasını sağladım.
-Navigation ile de sayfalar arasındaki geçiş durumlarını yönettim.

## Video

https://github.com/Cntrk01/BordoIOTask/assets/98031686/56970907-8bbc-4eb4-92d8-ed8aac3cc1fc

https://github.com/Cntrk01/BordoIOTask/assets/98031686/89ecf833-c077-4f0f-9a6b-e11a789c769c


## Görseller
![Ekran görüntüsü 2024-02-12 141332](https://github.com/Cntrk01/BordoIOTask/assets/98031686/d1bd60a4-4ad4-45c6-8564-108df4278cbe)
![Ekran görüntüsü 2024-02-12 141313](https://github.com/Cntrk01/BordoIOTask/assets/98031686/2fb0e464-2c68-493e-afbd-41efb6ff784d)
![Ekran görüntüsü 2024-02-12 141440](https://github.com/Cntrk01/BordoIOTask/assets/98031686/6660a771-8ec4-4e69-ae3f-17ebf22f314c)
![Ekran görüntüsü 2024-02-12 141435](https://github.com/Cntrk01/BordoIOTask/assets/98031686/b30450de-b7af-468c-a011-cd0d2890f6d6)
![Ekran görüntüsü 2024-02-12 141416](https://github.com/Cntrk01/BordoIOTask/assets/98031686/93d970ce-0dc0-40fe-88ec-9483395109c9)
![Ekran görüntüsü 2024-02-12 141408](https://github.com/Cntrk01/BordoIOTask/assets/98031686/a144a389-a5ad-4246-abdd-1ef81d1d7c9c)
![Ekran görüntüsü 2024-02-12 141401](https://github.com/Cntrk01/BordoIOTask/assets/98031686/c86b1d3f-4257-4300-807b-62d9ede3c958)
![Ekran görüntüsü 2024-02-12 141452](https://github.com/Cntrk01/BordoIOTask/assets/98031686/e52269e5-270a-4311-9cdb-ea8797da9f43)

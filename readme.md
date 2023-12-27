# GitHub Courier Tracking Service


## Kurulum

1. **Projeyi Klonlayın:**
    ```bash
       git clone https://github.com/mmsuerkan/CourierTrackingSystem.git
    cd CourierTrackingSystem
    ```

2. **Maven ile Derleme:**
    ```bash
    mvn clean install
    ```

3. **Uygulamayı Başlatma:**
    ```bash
    mvn spring-boot:run
    ```
   
## Veritabanı Ayarları
Veritabanı olarak postgresql kullanılmıştır.

kullanıcı adı: postgres
şifre: postgres
veritabanı adı: Tracking

CREATE TABLE couriers
(
id           SERIAL PRIMARY KEY,
name         VARCHAR(255) NOT NULL,
phone_number VARCHAR(20)  NOT NULL,
email        VARCHAR(255) NOT NULL
);
scripti ile kurye tablosu oluşturulmalıdır.

INSERT INTO couriers (name, phone_number, email)
VALUES ('John Doe', '123-456-7890', 'john.doe@example.com'),
('Jane Smith', '987-654-3210', 'jane.smith@example.com'),
('Bob Johnson', '555-123-4567', 'bob.johnson@example.com'),
('Alice Williams', '888-999-0000', 'alice.williams@example.com'),
('Charlie Brown', '111-222-3333', 'charlie.brown@example.com');

## Kullanım


**Uygulama başlarken önceden verilen stores.json dosyası ile mağazaları veritabanına kaydedilir.**

**Kurye konumlarını kaydetmek için POST isteği yapın.**
Örnek istek:

curl --location 'http://localhost:8080/api/location/log' \
--header 'Content-Type: application/json' \
--data '  {"courierId": 1, "latitude": 40.9623307, "longitude": 29.1244229, "timestamp": "2023-12-17T12:17:45"}
'
**Kurye veritabanında önceden tanımlı olmalıdır.**
**Mağazalar veritabanında önceden tanımlı olmalıdır.**

**Bu istekte 1 dakika içinde 100 metre içerisinde olan mağazaya birden fazla log isteği olduğunda
veritabanına kayıt olmayacaktır.**


**Kurye konumlarını sorgulamak için GET isteği yapın.**

curl --location --request GET 'http://localhost:8080/api/distance/calculateDistance/1' \
--header 'Content-Type: application/json' \
--data '{
"courierId": 1
}'

** Kurye veritabanında önceden tanımlı olmalıdır.
**Kuryenin veritabanındaki logları zamana göre sıralanır ve aralarındaki uzaklık hesaplanır ve toplanır.


## Veritabanı Modeli

Projede 3 adet tablo bulunmaktadır. Bunlar `courier`, `store`, `location_history`  tablolarıdır.

### Courier
- `id`: Kurye kimlik numarası
- `courierId`: Kurye numarası veya kimliği
- `lat`: Kuryenin enlem koordinatı
- `lng`: Kuryenin boylam koordinatı

### Store
- `id`: Mağaza kimlik numarası
- `name`: Mağaza adı
- `lat`: Mağazanın enlem koordinatı
- `lng`: Mağazanın boylam koordinatı

### LocationHistory
- `id`: Kurye konum geçmişi kimlik numarası
- `courierId`: Kurye numarası veya kimliği
- `lat`: Kuryenin enlem koordinatı
- `lng`: Kuryenin boylam koordinatı
- `timestamp`: Kuryenin konumunun kaydedildiği zaman
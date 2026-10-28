<p align="center"><img src="docs/logo.png" width="160" alt="FixLag"></p>

# FixLag

Mod **Fabric** client-side cho **Minecraft Java 26.2** giúp giảm lag, tăng FPS bằng một phím.

Nhấn **B** để chuyển vòng giữa 3 mức:

| Mức | Render distance | Hạt (particles) | Bóng thực thể | Biome blend | Khoảng cách vẽ thực thể | FPS giới hạn |
|---|---|---|---|---|---|---|
| OFF | như bạn cài | như bạn cài | như bạn cài | như bạn cài | như bạn cài | như bạn cài |
| LOW | tối đa 10 | giảm | tắt | tắt | 75% | như bạn cài |
| ULTRA | tối đa 6 | tối thiểu | tắt | tắt | 50% | không giới hạn |

- Cài đặt gốc của bạn được lưu lại và **khôi phục đúng** khi quay về OFF.
- Khi đang bật, góc trái trên hiện mức đang dùng và FPS hiện tại.
- Đổi phím ở *Options → Controls → Key Binds → FixLag*.
- Chỉ cần cài ở client, không cần cài lên server.

## Yêu cầu
Minecraft Java 26.2, Fabric Loader ≥ 0.19.3, Fabric API cho 26.2, Java 25.

## Build bằng GitHub
Đẩy toàn bộ lên GitHub, tab **Actions** → workflow **build** → tải jar ở mục **Artifacts**.

## Giấy phép
MIT

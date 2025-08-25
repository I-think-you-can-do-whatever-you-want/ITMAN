const SW_VERSION = 'v1.3.1';
const STATIC_CACHE = `static-${SW_VERSION}`;

self.addEventListener('install', (event) => {
    self.skipWaiting();
    event.waitUntil(
        caches.open(STATIC_CACHE).then((cache) => {
            return cache.addAll([
                '/pwa/start.html',   // 앱 진입용만 캐시
                '/pwa/offline.html', // 오프라인 대체 페이지
                '/pwa/icons/icon-192.png',
                '/pwa/icons/icon-310.png'
            ]);
        })
    );
});

self.addEventListener('activate', (event) => {
    clients.claim();
    event.waitUntil(
        caches.keys().then(keys =>
            Promise.all(keys.filter(k => k !== STATIC_CACHE).map(k => caches.delete(k)))
        )
    );
});

self.addEventListener('fetch', (event) => {
    const req = event.request;
    const url = new URL(req.url);

    if (!url.protocol.startsWith('http')) return;

    // manifest는 무조건 네트워크
    if (req.destination === 'manifest') {
        event.respondWith(fetch(req));
        return;
    }

    // HTML 문서 처리
    if (req.mode === 'navigate' || req.destination === 'document') {
        if (url.pathname === '/pwa/start.html') {
            // start.html만 캐시 우선
            event.respondWith(
                caches.match('/pwa/start.html').then(cached => cached || fetch(req))
            );
        } else {
            // 나머지 HTML은 오프라인 시 offline.html로 고정
            event.respondWith(
                fetch(req).catch(() => caches.match('/pwa/offline.html'))
            );
        }
        return;
    }

    // 정적 리소스 (CSS/JS/이미지 등)는 캐시 우선
    if (['style','script','image','font'].includes(req.destination)) {
        event.respondWith(
            caches.match(req, { ignoreSearch: true }).then(cached => {
                return cached || fetch(req).then(fresh => {
                    return caches.open(STATIC_CACHE).then(cache => {
                        cache.put(req, fresh.clone());
                        return fresh;
                    });
                });
            })
        );
        return;
    }

    // 그 외: 네트워크 없으면 offline.html
    event.respondWith(fetch(req).catch(() => caches.match('/pwa/offline.html')));
});

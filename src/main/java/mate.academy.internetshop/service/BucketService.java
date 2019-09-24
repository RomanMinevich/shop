package mate.academy.internetshop.service;

import mate.academy.internetshop.model.Bucket;

public interface BucketService {

    Bucket create(Bucket bucket);

    Bucket get(Long id);

    Bucket update(Bucket bucket);

    Bucket delete(Long id);

    Bucket addItem(Long bucketId, Long itemId);

    Bucket removeItem(Long bucketId, Long itemId);

    Bucket clear(Long bucketId);
}

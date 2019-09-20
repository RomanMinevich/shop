package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private static BucketDao bucketDao;
    @Inject
    private static ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long id) {
        return bucketDao.get(id);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket delete(Long id) {
        return bucketDao.delete(id);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = get(bucketId);
        Item item = itemDao.get(itemId);
        bucket.getItems().add(item);
        return update(bucket);
    }

    @Override
    public Bucket removeItem(Long bucketId, Long itemId) {
        Bucket bucket = get(bucketId);
        bucket.getItems().remove(itemDao.get(itemId));
        return bucket;
    }

    @Override
    public Bucket clear(Long id) {
        Bucket bucket = get(id);
        bucket.getItems().clear();
        return update(bucket);
    }

    @Override
    public List getAllItems(Long id) {
        List<Item> items = List.copyOf(get(id).getItems());
        clear(id);
        return items;
    }
}

package mate.academy.internetshop.dao.impl;

import static java.util.stream.IntStream.range;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoImpl implements ItemDao {

    @Override
    public Item create(Item item) {
        if (Storage.items
                .stream()
                .anyMatch(element -> element.getId().equals(item.getId()))) {
            throw new IllegalArgumentException("Can't add item with id " + item.getId());
        }
        Storage.items.add(item);
        return item;
    }

    @Override
    public Item get(Long id) {
        return Storage.items
                .stream()
                .filter(element -> element.getId().equals(id))
                .findAny()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find item with id " + id));
    }

    @Override
    public Item update(Item item) {
        int index = range(0, Storage.items.size())
                 .filter(element -> Storage.items.get(element).getId().equals(item.getId()))
                 .findAny()
                 .orElseThrow(() ->
                         new NoSuchElementException("Cant't find item with id " + item.getId()));
        Storage.items.set(index, item);
        return item;
    }

    @Override
    public Item delete(Long id) {
        Item item = get(id);
        Storage.items.removeIf(element -> element.equals(item));
        return item;
    }

    @Override
    public List<Item> getAll() {
        return Storage.items;
    }
}

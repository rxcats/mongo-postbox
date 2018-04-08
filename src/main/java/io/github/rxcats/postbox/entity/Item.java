package io.github.rxcats.postbox.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Item {

    private String itemId;

    private long quantity;

}

package com.corith.lgchicken.models;

import com.corith.lgchicken.enums.GroupType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardGroup {
    GroupType groupType;
    List<Card> cards;
    int points;
    double weight;

    CardGroup(GroupType groupType) {
        this.groupType = groupType;
        cards = new ArrayList<>();
        points = 0;
        weight = 0.0;
    }
}

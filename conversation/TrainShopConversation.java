package conversation;

import support.GeneralProperty;
import support.Property;

import java.util.ArrayList;
import java.util.List;

public class TrainShopConversation extends Conversation {
    protected final static List<Property> propertyList = new ArrayList<>();
    public List<Property> getProperties() {return propertyList;}

    static {
        propertyList.addAll(Conversation.propertyList);
        propertyList.add(GeneralProperty.TRAIN_SHOP);
    }
}
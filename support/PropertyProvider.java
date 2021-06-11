package support;

import java.util.List;

public interface PropertyProvider {
    List<Property> getProperties();

    default Property getLastProperty() {
        List<Property> properties = getProperties();
        int size = properties.size();
        if (size > 0) {
            return properties.get(size - 1);
        }
        return GeneralProperty.OTHER;
    }
}

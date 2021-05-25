package project.kien.restaurantmanagementsystemapi.utils.tools;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SessionNumberGenerator {
    public String generate(String positionNumber) {
        return positionNumber + System.currentTimeMillis();
    }
}

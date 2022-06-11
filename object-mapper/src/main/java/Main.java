import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dto.Car;
import dto.User;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String args[]) throws JsonProcessingException {
        System.out.println("main");

        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("k5");
        car1.setCarNumber("11가 1111");
        car1.setType("sedan");


        Car car2 = new Car();
        car2.setName("q5");
        car2.setCarNumber("22가 1111");
        car2.setType("SUV");

        List<Car> carList = Arrays.asList(car1,car2);
        user.setCars(carList);

        System.out.println(user);
        
        // json의 구조를 알고 있을 떄 사용 가능
        // json 내부 구조 값을 변경가능
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();
        System.out.println("name : "+_name);
        System.out.println("age : "+_age);
        
        // json에서 오브젝트 접근시
        JsonNode cars = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) cars;
        List<Car>_cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_cars);
        
        // json 내부 값 바꿀 때 사용방법
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name","mjh");
        objectNode.put("age",20);

        System.out.println(objectNode.toPrettyString());
    }
}

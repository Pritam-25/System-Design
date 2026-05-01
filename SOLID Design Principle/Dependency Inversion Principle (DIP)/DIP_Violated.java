// Low-level module
class MySQLDatabase {
    public void saveToSQL(String data) {
        System.out.println("Executing SQL Query: INSERT INTO users VALUES('" + data + "');");
    }
}

// Low-level module
class MongoDBDatabase {
    public void saveToMongo(String data) {
        System.out.println("Executing MongoDB Function: db.users.insert({name: '" + data + "'})");
    }
}

// High-level module (Tightly coupled)
class UserService {
    private final MySQLDatabase sqlDb = new MySQLDatabase();
    private final MongoDBDatabase mongoDb = new MongoDBDatabase();

    public void storeUserToSQL(String user) {
        // MySQL-specific code
        sqlDb.saveToSQL(user);
    }

    public void storeUserToMongo(String user) {
        // MongoDB-specific code
        mongoDb.saveToMongo(user);
    }
}

public class DIP_Violated {
    public static void main(String[] args) {
        UserService service = new UserService();
        service.storeUserToSQL("Aditya");
        service.storeUserToMongo("Rohit");
    }
}
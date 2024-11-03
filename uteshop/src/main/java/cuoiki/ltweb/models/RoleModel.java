package cuoiki.ltweb.models;

public class RoleModel {
	private Long id;
    private String name;

    // Default constructor
    public RoleModel() {
    }

    // Parameterized constructor
    public RoleModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

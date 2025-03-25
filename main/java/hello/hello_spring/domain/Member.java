package hello.hello_spring.domain;

public class Member {
    private Long id; // 요구사항 기반한 id와 name 식별자임.
    private String name;

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

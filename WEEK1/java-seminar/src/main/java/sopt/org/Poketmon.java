package sopt.org;

/**
 * abstract : 반복되는 속성, 메소드를 상속해서 사용하도록 하기 위해 추상화 클래스로 만들 수 있다!
 * -> 기존 기능의 확장성 GOOD
 */
public abstract class Poketmon {

    private String name;   // private: 클래스 내부에서만 사용가능하게 하는 접근제어자
    private PoketmonType type;

    // 생성자
    public Poketmon(String name, PoketmonType type) {
        this.name = name;
        this.type = type;
    }

    public Poketmon(PoketmonType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public PoketmonType getType() {
        return type;
    }
}

class bai6_Animal {
    String name;
    int weight;

    public bai6_Animal(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public String getWeight() {
        return weight + " kg";
    }

    public void displayInfo() {
        System.out.println(name + " nặng " + weight + " cân");
    }

}

class Lions extends bai6_Animal {
    private int eat;
    
    public Lions(String name, int weight, int eat) {
        super(name, weight);
        this.eat = eat;
    }

    @Override
    public void displayInfo() {
        System.out.println("Sư tử " + name + " nặng " + weight + " cân và ăn " + eat + " cân thịt mỗi ngày");
    }
}

class Snake extends bai6_Animal {
    private int length;

    public Snake(String name, int weight, int length) {
        super(name, weight);
        this.length = length;
    }

    @Override
    public void displayInfo() {
        System.out.println("Rắn " + name + " nặng " + weight + " cân và dài " + length + " mét");
    }
}

class Monkey extends bai6_Animal {
    private String favoriteFood;

    public Monkey(String name, int weight, String favoriteFood) {
        super(name, weight);
        this.favoriteFood = favoriteFood;
    }

    @Override
    public void displayInfo() {
        System.out.println("Khỉ " + name + " nặng " + weight + " cân và thích ăn " + favoriteFood);
    }
}



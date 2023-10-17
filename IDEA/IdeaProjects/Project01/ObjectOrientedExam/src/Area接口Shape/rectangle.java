package Area接口Shape;

class rectangle implements Shape{

    private double width;

    public rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    private double height;//长

    public double getArea(){                     //Rectangle类求面积
        return width*height;
    }
    public double getCircumference(){            //Rectangle类求周长
        return 2*(width+height);
    }
}

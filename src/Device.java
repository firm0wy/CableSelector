public class Device {
    public double nominalCurrent=0;
    public int[] nominalCurrentArray={6,10,16,20,25,32,40,50,63,80,100,125,160,200,224,250,300,315,355,400,500,630,800,1000};

    public Device(){
    }
    public void selectDeviceNominalCurrent(double loadCurrent){
        int i=0;
        while(nominalCurrent<loadCurrent){
            nominalCurrent=nominalCurrentArray[i];
            i++;
            //System.out.println(nominalCurrent);
        }
    }
}


import java.lang.Math.*;
public class Cable {
    public String type;
    String insulation;
    String material;
    boolean inflammable; //N2XH
    boolean flameresistant; //NXHX
    double dV;
    double cableDiameter=1;
    double diameterArray[] = {1.5, 2.5, 4, 6, 10, 16, 25, 35, 50, 70, 95, 120, 150, 185, 240, 300};
    //  D1
    double D1_PVC_CU3[] = {18, 24, 31, 39, 52, 67, 86, 103, 122, 151, 179, 203, 230, 258, 297, 336};
    double D1_PVC_AL3[] = {0, 18.5, 24, 30, 40, 52, 66, 80, 94, 117, 138, 157, 178, 200, 230, 260};
    double D1_XLPE_CU3[] = {22, 29, 37, 46, 61, 79, 101, 122, 144, 178, 211, 240, 271, 304, 351, 396};
    double D1_XLPE_AL3[] = {0, 22, 29, 36, 47, 61, 78, 94, 112, 138, 164, 186, 210, 236, 272, 308};
    //  D2


    //  E
    double E_PVC_CU3[] = {18.5, 25 ,34, 43, 60, 80, 101, 126, 153, 196, 238, 276, 318, 362, 424 };
    double E_PVC_AL3[] = {0, 19.5, 26, 33, 46, 61, 78, 96 ,117, 150, 183, 212, 245, 280, 330};
    double E_XLPE_CU3[] = {23, 31, 42, 54, 75, 100, 127, 158, 192, 246, 298, 346, 395, 450, 538};
    double E_XLPE_AL3[] = {0, 26, 35, 45, 62, 84, 101, 126, 154, 198, 241, 280, 324, 371, 439};
    //  F
    double F_PVC_CU3[] = {0,0, 0, 0 ,0, 0, 110 ,137, 167, 216, 264, 308, 356, 409, 485, 561};
    double F_PVC_AL3[] = {0, 0, 0, 0, 0, 0, 84, 105, 128, 166, 203, 237, 274, 315 ,375 ,434};
    double F_XLPE_CU3[] = {0, 0 ,0, 0, 0, 0,141,176, 216, 279, 342, 400, 464, 533, 634, 736};
    double F_XLPE_AL3[] = {0, 0, 0, 0, 0, 0, 107, 135, 165, 215, 264, 308, 358, 413, 492};


    double calculateddV(double loadCurrent, double cableLength, double cableDiameter, String material, String phases, int numberOfCables){
        if (material.equals("Cu")) {
            if (phases.equals("3")) {
                return dV = (Math.sqrt(3) * loadCurrent * cableLength * 100) / (58 * cableDiameter * 400)/(double) numberOfCables;
            }
            else {
                return dV = 2*(Math.sqrt(3) * loadCurrent * cableLength * 100) / (58 * cableDiameter * 230)/(double)numberOfCables;
            }
        }
        else{
            if (phases.equals("3")) {
                return dV = (Math.sqrt(3) * loadCurrent * cableLength * 100) / (38 * cableDiameter * 400)/(double)numberOfCables;
            }
            else {
                return dV = 2*(Math.sqrt(3) * loadCurrent * cableLength * 100) / (38 * cableDiameter * 230)/(double)numberOfCables;
            }

        }
    }


    Cable(String insulation, String material, boolean inflammable, boolean flameresistant, String layingType, double deviceCurrent, double actingCurrent, double maxdV, double loadCurrent, double cableLength,String phases, int numberOfCables) {
        this.insulation = insulation;
        this.material = material;
        this.inflammable = inflammable;
        this.flameresistant = flameresistant;
        double cableFactor=Math.pow(0.94,numberOfCables-1)*numberOfCables;
        System.out.println(cableFactor);



        if (insulation.equals("PVC")) {
            if (material.equals(("Cu"))) {
                type = "YKY";
            } else {
                type = "YAKY";
            }
        } else if (insulation.equals("XLPE")) {
            if (material.equals(("Cu"))) {
                if (flameresistant == true) {
                    type = "NHXH";
                } else if (inflammable == true) {
                    type = "N2XH";
                } else {
                    type = "YKXS";
                }
            } else {
                type = "YAKXS";
            }
        }
        int i = 0;
        try {
            if (layingType.equals("D1")) {
                switch (type) {
                    case "YAKY": {

                        while ((deviceCurrent > (D1_PVC_AL3[i]*cableFactor))|| (actingCurrent > (D1_PVC_AL3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "YKY": {

                        while ((deviceCurrent > (D1_PVC_CU3[i]*cableFactor)) || (actingCurrent > (D1_PVC_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }

                    }
                    case "YAKXS": {

                        while ((deviceCurrent > (D1_XLPE_AL3[i]*cableFactor)) || (actingCurrent > (D1_XLPE_AL3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "YKXS": {

                        while ((deviceCurrent > (D1_XLPE_CU3[i]*cableFactor)) || (actingCurrent >( D1_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "N2XH": {

                        while ((deviceCurrent > (D1_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (D1_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "NHXH": {

                        while ((deviceCurrent > (D1_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (D1_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                }
            }
            else if (layingType.equals("E")) {
                switch (type) {
                    case "YAKY": {

                        while ((deviceCurrent > (E_PVC_AL3[i]*cableFactor)) || (actingCurrent > (E_PVC_AL3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "YKY": {

                        while ((deviceCurrent > (E_PVC_CU3[i]*cableFactor)) || (actingCurrent > (E_PVC_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }

                    }
                    case "YAKXS": {

                        while ((deviceCurrent > (E_XLPE_AL3[i]*cableFactor)) || (actingCurrent > (E_XLPE_AL3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "YKXS": {

                        while ((deviceCurrent > (E_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (E_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "N2XH": {

                        while ((deviceCurrent > (E_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (E_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "NHXH": {

                        while ((deviceCurrent > (E_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (E_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                }
            }
            else if (layingType.equals("F")) {
                switch (type) {
                    case "YAKY": {

                        while ((deviceCurrent > (F_PVC_AL3[i]*cableFactor)) || (actingCurrent > (F_PVC_AL3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "YKY": {

                        while ((deviceCurrent > (F_PVC_CU3[i]*cableFactor)) || (actingCurrent > (F_PVC_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }

                    }
                    case "YAKXS": {

                        while ((deviceCurrent > (F_XLPE_AL3[i]*cableFactor)) || (actingCurrent > (F_XLPE_AL3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "YKXS": {

                        while ((deviceCurrent > (F_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (F_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "N2XH": {

                        while ((deviceCurrent > (F_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (F_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                    case "NHXH": {

                        while ((deviceCurrent > (F_XLPE_CU3[i]*cableFactor)) || (actingCurrent > (F_XLPE_CU3[i] * 1.45*cableFactor))|| calculateddV(loadCurrent,cableLength,cableDiameter,material,phases,numberOfCables)>maxdV) {
                            i++;
                            cableDiameter = diameterArray[i];

                        }
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException exception){
            type="OUT OF POSSIBLE DIAMETER!!!";
            cableDiameter=0;
        }


        if (material.equals(("Cu"))) {
            dV=(Math.sqrt(3)*loadCurrent*cableLength*100)/(58*cableDiameter*400);
        }
    }
}
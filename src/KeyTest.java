import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;

public class KeyTest {
    public byte[] generateSequence(int length) {
        int byteLength = (length + 7) / 8;
        byte[] randomBytes = new byte[byteLength];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomBytes);
        byte[] sequence = new byte[length];
        int sequenceIndex = 0;
        for (int i = 0; i < byteLength; i++) {
            int currentByte = randomBytes[i] & 0xFF; // Convert to an unsigned byte
            for (int bit = 7; bit >= 0 && sequenceIndex < length; bit--) {
                sequence[sequenceIndex] = (byte) ((currentByte >> bit) & 1);
                sequenceIndex++;
            }
        }
        return sequence;
    }

    public boolean monobitTest(byte[] array){
        int numZeros = 0;
        for(byte element : array){
            if(element == 0){
                numZeros++;
            }
            if(element != 0 && element !=1){
                throw new IllegalArgumentException("Invalid sequence");
            }
        }
        return numZeros > 9654 && numZeros < 10346;
    }

    private boolean maxLengthSeriesTest(byte[] sequence) {
        int maxSeries = 0;
        int currentSeries = 0;
        if (sequence.length == 0) {
            return false;
        }

        int temp = sequence[0];
        for(int i =1; i < sequence.length; i++){
            if(temp == sequence[i]){
                currentSeries++;
            }
            else {
                if(currentSeries> maxSeries){
                    maxSeries = currentSeries;
                }
                currentSeries = 0;
            }
            temp = sequence[i];
        }
        if (currentSeries > maxSeries) {
            maxSeries = currentSeries;
        }
        return maxSeries <= 36;
    }
    private boolean pokkerTest(byte[] sequence) {
        HashMap<String,Integer> frequency = new HashMap<>();
        for(int i = 0; i<sequence.length; i+=4){
             byte[] blockArray = Arrays.copyOfRange(sequence, i, i + 4);
             String block = arrayToString(blockArray);
            if(!frequency.containsKey(block)){
                frequency.put(block,1);
            }
            else{
                frequency.put(block,frequency.get(block)+1);
            }
        }
        System.out.println(frequency);
        int sum = frequency.values().stream()
                .mapToInt(value -> (int) Math.pow(value, 2))
                .sum();
        double resultValue = ((Math.pow(2, 4) / (20000.0 / 4)) * sum) - (20000.0 / 4);
        return resultValue>= 1.03 && resultValue <= 57.4;
    }
    private String arrayToString(byte[] array){
        StringBuilder res = new StringBuilder();
        for(byte element : array){
            res.append(element);
        }
        return  res.toString();
    }

    private boolean seriesLengthTest(byte[] sequence) {
        int currentSeries = 1;
        int seriesLengths = 6;
        int[] zeroFrequency = new int[seriesLengths];
        int[] onesFrequency = new int[seriesLengths];

        for(int i = 0; i< sequence.length-1; i++){
           if(sequence[i] == sequence[i+1]){
               currentSeries++;
           }
           else {
               int seriesLength = Math.min(currentSeries,seriesLengths);
               if(sequence[i] == 0){
                   zeroFrequency[seriesLength -1]++;
               }
               if(sequence[i] == 1){
                   onesFrequency[seriesLength -1]++;
               }
               currentSeries =1;
           }
        }
        int seriesLength = Math.min(currentSeries,seriesLengths);
        if(sequence[sequence.length-1] == 0){
            zeroFrequency[seriesLength -1]++;
        }
        if(sequence[sequence.length-1] == 1){
            onesFrequency[seriesLength -1]++;
        }
        System.out.println("Zero's frequency: " + Arrays.toString(zeroFrequency));
        System.out.println("Once's frequency: " + Arrays.toString(onesFrequency));
        return validateResult(onesFrequency)&&validateResult(zeroFrequency);
    }
    public  boolean validateResult(int[] arr) {
        return arr[0] >= 2267 && arr[0] <= 2733 &&
                arr[1] >= 1079 && arr[1] <= 1421 &&
                arr[2] >= 502 && arr[2] <= 748 &&
                arr[3] >= 223 && arr[3] <= 402 &&
                arr[4] >= 90 && arr[4] <= 223 &&
                arr[5] >= 90 && arr[5] <= 223;
    }

    public String testRunner(byte[] sequence) {

        if(!monobitTest(sequence)) {
            return "Test 1 failed";
        }
        if(!maxLengthSeriesTest(sequence)) {
            return "Test 2 failed";
        }
        if(!seriesLengthTest(sequence)) {
            return "Test 3 failed";
        }
        if(!pokkerTest(sequence)) {
            return "Test 4 failed";
        }
        return "Sequence successfully passed all the tests, the bits are enough random";
    }

}


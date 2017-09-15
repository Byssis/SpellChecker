import java.nio.charset.Charset;

/**
 * Created by Albin on 2017-09-15.
 */
public class HashCalculator {

    public static long hash(String data, int i){
        return hash(data.getBytes(), i);
    }

    public static long hash( byte[] data, int i){
        switch (i) {
            case 0:


            case 1:


            case 2:


            case 3:


            case 4:


            case 5:


            case 6:


            case 7:


            case 8:


            default:
                return bad_hash(data , i);
        }
    }

    private static long bad_hash(byte[] data, int hashFunction){
        long p = HASH_TABLE[hashFunction];
        long hash = p;

        for (int i = 0; i < data.length; i++)
            hash = (hash ^ data[i]) * p;

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        return Math.abs(hash);
    }

    private static final int [] HASH_TABLE = {157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};


}

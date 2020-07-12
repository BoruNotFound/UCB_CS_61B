import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static GuitarString[] guitarStrings = new GuitarString[37];

    private static void initStrings() {
        for (int i = 0; i < 37; i++) {
            double freq = 440 * Math.pow(2, (i - 24.0) / 12.0);
            guitarStrings[i] = new GuitarString(freq);
        }
    }

    public static void main(String args[]) {
        initStrings();

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int idx = keyboard.indexOf(key);
                if (idx != -1) {
                    guitarStrings[idx].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < 37; i++) {
                sample += guitarStrings[i].sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < 37; i++) {
                guitarStrings[i].tic();
            }

        }
    }
}

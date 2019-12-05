import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This class scans the selected level, and returns the variables
 * 
 * @author Ben Hyde
 *
 */
public class ReadLevelFile {

    /**
     * Scans each line within the txt file, and adds it to the designated array.
     * 
     * @param in The scanner with the txt file.
     * @return returns an instance of the level, with the map and entitiy arrays
     *         full.
     * 
     */
    private static Level readDataFile(Scanner in) {

	Queue<Entity> entityQueue = new Queue<Entity>();

	String name = in.nextLine();

	String size = in.nextLine();
	int width = Integer.parseInt(size.split(" ")[0]);
	int height = Integer.parseInt(size.split(" ")[1]);

	String[][] map = new String[width][height];

	for (int h = 0; h < height; h++) {

	    String l = in.nextLine();

	    for (int w = 0; w < width; w++) {
		String cell = (l.split("")[w]);
		map[w][h] = cell;
	    }

	}

	String startLine = in.nextLine();
	int xStart = Integer.parseInt(startLine.split(" ")[1]);
	int yStart = Integer.parseInt(startLine.split(" ")[2]);

	while (in.hasNextLine()) {

	    String l = in.nextLine();
	    System.out.println(l);

	    Scanner line = new Scanner(l);
	    String ent = line.next().toLowerCase();

	    String entity = ent.substring(0, 1).toUpperCase()
		    + ent.substring(1);

	    try {
		Class.forName(entity);
	    } catch (ClassNotFoundException e) {
		System.out.println("Error: " + entity + " class needed.");
	    }

	    if (entity.equalsIgnoreCase("DUMB")) {
		entityQueue.enqueue(readDumb(line));
	    } else if (entity.equalsIgnoreCase("DOOR")) {

		entityQueue.enqueue(readEntity(line));
	    } else if (entity.equalsIgnoreCase("FIREBOOTS")) {
		entityQueue.enqueue(readFireBoots(line));
	    } else if (entity.equalsIgnoreCase("TOKEN")) {

		entityQueue.enqueue(readToken(line));
	    } else if (entity.equalsIgnoreCase("TELEPORTER")) {

		entityQueue.enqueue(readEntity(line));
	    } else if (entity.equalsIgnoreCase("KEY")) {
		entityQueue.enqueue(readKey(line));
	    } else if (entity.equalsIgnoreCase("FLIPPERS")) {

		entityQueue.enqueue(readFlippers(line));
	    } else if (entity.equalsIgnoreCase("SMART")) {
		entityQueue.enqueue(readSmart(line));
	    } else if (entity.equalsIgnoreCase("STRAIGHT")) {
		entityQueue.enqueue(readStraight(line));
	    } else {
		System.out.println("Error: Entity not found.");
	    }

	}

	Level level = new Level(name, width, height, xStart, yStart, map,
		entityQueue);

	return level;

    }

    public static Entity readDumb(Scanner line) {
	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	DumbFollowEnemy dumbEnemy = new DumbFollowEnemy(x, y);
	return dumbEnemy;
    }

    public static Entity readStraight(Scanner line) {
	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	boolean moveInX = Boolean.parseBoolean(line.next());
	boolean positiveDirection = Boolean.parseBoolean(line.next());
	StraightLineEnemy straightEnemy = new StraightLineEnemy(x, y, moveInX,
		positiveDirection);
	return straightEnemy;
    }

    public static Entity readSmart(Scanner line) {
	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	SmartFollowEnemy smartEnemy = new SmartFollowEnemy(x, y);
	return smartEnemy;
    }

    public static Entity readEntity(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());

	Entity entity = new Entity(x, y);

	return entity;

    }

    public static Token readToken(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());

	Token token = new Token(x, y);

	return token;

    }

    public static Fireboots readFireBoots(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());

	Fireboots fireBoots = new Fireboots(x, y);

	return fireBoots;

    }

    public static Flippers readFlippers(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());

	Flippers flippers = new Flippers(x, y);

	return flippers;

    }

    public static Key readKey(Scanner line) {

	int x = Integer.parseInt(line.next());
	int y = Integer.parseInt(line.next());
	String col = line.next();

	Key key = new Key(x, y, col);

	return key;

    }

    public static Level readDataFile(String filename)
	    throws NoSuchElementException {

	Scanner in = null;

	// Checks that the file exists.
	try {
	    in = new Scanner(new File(filename));
	    System.out.println("File found.\n");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}

	return ReadLevelFile.readDataFile(in);

    }

}

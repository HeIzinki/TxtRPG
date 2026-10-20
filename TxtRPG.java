import java.util.Scanner;
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class TxtRPG {
  // Stop java warnings from showing up
  @SuppressWarnings("all")
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    Location[] locations = new Location[11];
    locations[0] = new Location("field", "Open Field", "You enter a lush prairie with flowers abloom.", new String[]{"swamp", "peaks", "village"});
    locations[1] = new Location("peaks", "Perilous Peaks", "You come across a dangerous looking mountainous ridgeline.", new String[]{"field", "lair"});
    locations[2] = new Location("swamp", "Slimy Swamp", "You trudge through a humid foggy swamp with low visibility.", new String[]{"field", "hut"});
    locations[3] = new Location("village", "Seaside Village", "You enter a quaint village bustling with activity near the sea.", new String[]{"field", "shore", "castle"});
    locations[4] = new Location("hut", "Witches Hut", "You arrive at a creaky derilict old wooden hut.", new String[]{"swamp"});
    locations[5] = new Location("shore", "Sandy Shore", "You arrive at a beautiful beach with blue skies and glistening water.", new String[]{"village"});
    locations[6] = new Location("castle", "Towering Castle", "You arrive at a resplendent and expansive castle.", new String[]{"village", "ltower", "rtower", "dungeon"});
    locations[7] = new Location("ltower", "Left Tower", "You climb the left tower of the castle, it is narrow and tall structure", new String[]{"castle"});
    locations[8] = new Location("rtower", "Right Tower", "You climb the right tower of the castle, it is wide and a cool breeze is blowing from the windows", new String[]{"castle"});
    locations[9] = new Location("dungeon", "Dastardly Dungeon", "You descend into a dark, dank dungeon. You notice a secret tunnel.", new String[]{"castle", "shore"});
    locations[10] = new Location("lair", "Dragons Lair", "You ascend into a steep lofty den. The bones of fallen heroes litter the floor. ", new String[]{"peaks"});


    Item[] items = new Item[10];
    items[0] = new Item("torch", "Torch", "a bright torch", "ltower");
    items[1] = new Item("sword", "Sword", "a sharp sword", "villager");
    items[2] = new Item("apple", "Apple", "a tasty apple", "field");
    items[3] = new Item("shield", "Shield", "a sturdy shield", "castle");
    items[4] = new Item("gold", "Gold", "a hunk of gold", "prisoner");
    items[5] = new Item("armor", "Armor", "heavy protective armor", "blacksmith");
    items[6] = new Item("healthpotion", "Health Potion", "a vile tasting potion", "witch");
    items[7] = new Item("broom", "Broom", "a sturdy broom", "swamp");
    items[8] = new Item("seashell", "Seashell", "a small seashell", "shore");
    items[9] = new Item("rat", "Dead Rat", "a dead rat", "rtower");



    Creature[] creatures = new Creature[7];
    creatures[0] = new Creature("you", "You buddy!", "A mirror image of yourself", "You hear your innner voice", "field", new String[]{""}, "", "");
    //  creatures[0] = new Creature("you", "You buddy!", "A mirror image of yourself", "You hear your innner voice", "field", new String[]{"","sword","armor","shield","healthpotion"}, "", "");
    creatures[1] = new Creature("villager", "Villager", "A kind villager", "Welcome to Seaside Village! I'll give you my father's old" + ANSI_YELLOW + " Sword " + ANSI_PURPLE + "for an " + ANSI_YELLOW + "Apple." + ANSI_RESET, "village", new String[]{"sword"}, "apple", "sword"); 
    creatures[2] = new Creature("blacksmith", "Blacksmith", "A Strong Blacksmith", "Welcome to Seaside Village! You look like a new adventurer! I will give you some" + ANSI_YELLOW + " armor" + ANSI_PURPLE + " if you sweep my shop." + ANSI_RESET, "village", new String[]{"armor"}, "broom", "armor");
    creatures[3] = new Creature("witch", "Witch", "A Gnarly Witch", "A" + ANSI_YELLOW + " potion" + ANSI_PURPLE + " costs one piece of " + ANSI_YELLOW + "gold" + ANSI_PURPLE + "!" + ANSI_RESET, "hut", new String[]{"healthpotion"}, "gold", "healthpotion"); 
    creatures[4] = new Creature("prisoner", "Prisoner", "A Malnourished Prisoner", "It's so dark in here. Ill give you some" + ANSI_YELLOW + " gold" + ANSI_PURPLE + " if you can light the room." + ANSI_RESET, "dungeon", new String[]{"gold"}, "torch", "gold");
    creatures[5] = new Creature("dragon", "Dragon", "An Immense Dragon", "You have come to fight, Yes? Lets Dance.", "lair", new String[]{""}, "", "");
    creatures[6] = new Creature("traveler", "Traveler", "A Knowing Traveler", "Do not be a fool. The dragon will rip you to shreds if you are not fully prepared. You will need a" + ANSI_YELLOW + " Sword" + ANSI_PURPLE + ", a"  + ANSI_YELLOW + " Shield" + ANSI_PURPLE + ", " + ANSI_YELLOW + "Armor" + ANSI_PURPLE + ", and a " + ANSI_YELLOW + "Healthpotion." + ANSI_RESET, "peaks", new String[]{""}, "", "");


    Creature you = creatures[0];
    String currentLocationId = you.locationId;
    for(;;) {
      clear();
      if (currentLocationId.equals("exit")) { break; }
      int locationIndex = getLocation(locations, currentLocationId);
      Location loc = locations[locationIndex];
      out("You have entered " + ANSI_RED  + loc.name + ANSI_RESET);
      out(loc.description);
      // get rooms objects
      String stuffInRoom = loc.getItemIds(items);
      String stuffDescriptions = "";
      if (stuffInRoom.isEmpty()) {
        out("The area is empty.");
      } else {
        stuffDescriptions = loc.getItemDescriptions(items);
        out("You see the following: " + stuffDescriptions);
      }
      // get room creatures (except you)
      String creaturesInRoom = loc.getCreatures(creatures);
      if (creaturesInRoom.isEmpty()) {
        out("There is nobody here.");
      } else {
        out("You see : " + creaturesInRoom);
      }

      String exits = "";
      String prettyExits = "";
      String nextLocationId = "";
      for (int e = 0; e < loc.exits.length; e++) {
        prettyExits += locations[getLocation(locations, loc.exits[e])].name + ", ";
        exits += loc.exits[e] + ", ";
      }
      // remove last comma from exits
      exits = exits.substring(0, exits.length() - 2);
      prettyExits = prettyExits.substring(0, prettyExits.length() - 2);
      // output menu choices to user
      out(menuOutput(prettyExits, exits, stuffDescriptions, stuffInRoom, creaturesInRoom));
      // get user input
      String response = input.nextLine();
      if (response.equals("exit")) { break; }

      if (response.equals("talk dragon")) {
        String talkCreature = response.substring(5, (response.length()));
        out("\nThe " + talkCreature + " says: " + ANSI_PURPLE);
        String creatureSays = getCreatureDialogue(talkCreature, creatures, items); 
        out(creatureSays + ANSI_RESET);
        out(ANSI_GREEN);
        // if  inventory has sword, shield, armor, potion     creatures[0].inventory() 
        if (creatures[0].inventory().contains("shield")) {
          out("The dragon breathes fire towards you and you block it with your shield!\n");
          playSound("dragon-fire.mp3");
          wait(2000);
        }
        if (creatures[0].inventory().contains("armor")) {
          out("The dragons claws glance against your armor, which protects you from being shredded to pieces.\n");
          playSound("ArmorScrape.mp3");
          wait(2000);
        }
        if (creatures[0].inventory().contains("healthpotion")) {
          out("You drink a health potion to reinvigorate yourself to continue battling the dragon.\n");
          playSound("HealthPotion.mp3");
          wait(2000);
        }
        if (creatures[0].inventory().contains("sword")) {
          out("You stab the dragon with your sword!\n");
          playSound("SwordSlash.mp3");
        }
        out(ANSI_RESET);

        if (creatures[0].inventory().contains("sword") &&
            creatures[0].inventory().contains("shield") &&
            creatures[0].inventory().contains("armor") &&
            creatures[0].inventory().contains("healthpotion")) {
          out(ANSI_RED + "You killed the Dragon! You win the game!" + ANSI_RESET); 
          // cheesy easy bell sound for victory
          // for (int x = 0; x < 100; x++) {
          //   System.out.print("\007");
          //   wait(25);
          // }
          
          // fancy sound of victory
          playSound("victory.mp3");
          break;
        } else {
          out(ANSI_RED + "The Dragon kills you." + ANSI_RESET);
          break;
        }
      }
      // check to see if the user is trying to talk to a creature
      else nextLocationId = talkMenuInput(response, you, currentLocationId, creatures, loc, items);

      // check to see if the user is trying to pickup an item
      nextLocationId = pickupMenuInput(response, you, currentLocationId, items, loc);

      // now check to make sure location is valid, repeat menu choices if it is not
      while ((validLocation(locations, nextLocationId) == false) || (accessibleLocation(loc, nextLocationId) == false)) {
        // re-set the stuff in room in case they picked something up
        stuffInRoom = loc.getItemIds(items);
        stuffDescriptions = "";
        if (stuffInRoom.isEmpty()) {
          out("The area is empty.");
        } else {
          stuffDescriptions = loc.getItemDescriptions(items);
          out("You see the following: " + stuffDescriptions);
        }
        // output menu choices to user
        out(menuOutput(prettyExits, exits, stuffDescriptions, stuffInRoom, creaturesInRoom));
        response = input.nextLine();
        if (response.equals("exit")) { break; }
        nextLocationId = pickupMenuInput(response, you, currentLocationId, items, loc);
        nextLocationId = talkMenuInput(response, you, currentLocationId, creatures, loc, items);
      }
      currentLocationId = nextLocationId;
    }

  }

  private static String talkMenuInput(String response, Creature you, String currentLocationId, Creature[] creatures, Location loc, Item[] items) {
    String returnVal = "";

    // check to see if the input is for talking to a creature
    if ((response.length() > 5) && (response.substring(0, 4).equals("talk"))) {
      String talkCreature = response.substring(5, (response.length()));
      if (creatureInLocation(talkCreature, loc, you, creatures)) {
        out("\nThe " + talkCreature + " says: " + ANSI_PURPLE);
        String creatureSays = getCreatureDialogue(talkCreature, creatures, items); 
        out(creatureSays + ANSI_RESET);
      } else {
        out("Creature is not in this location");
      }
      returnVal = currentLocationId;
    } else { // since not a creature conversationreturn the response to go through other menu checks
      returnVal = response;
    }

    return returnVal;
  }

  private static String pickupMenuInput(String response, Creature you, String currentLocationId, Item[] items, Location loc) {
    String returnVal = "";

    // check to see if the input is for picking up an item
    if ((response.length() > 7) && (response.substring(0, 6).equals("pickup"))) {
      String pickupItem = response.substring(7, (response.length()));
      if (itemInLocation(pickupItem, loc, you, items)) {
        String inventory = you.pickupItem(pickupItem, items);
        out("You picked up the " + pickupItem);
        out("Your inventory is : " + inventory);
      } else {
        out("Item is not in this location");
      }
      returnVal = currentLocationId;
    } else if (response.equals("inventory")) {
      out("Your inventory is : " + you.inventory());
      returnVal = currentLocationId;
    } else { // since not a pickup return the response to go through other menu checks
      returnVal = response;
    }
    return returnVal;
  }

  private static Boolean itemInLocation(String pickupItem, Location currentLocation, Creature you, Item[] items) {
    // check to make sure the item requested is in the location of the user
    Boolean returnVal = false;
    String stuffInRoom = currentLocation.getItemIds(items);
    if (stuffInRoom.contains(pickupItem)) {
      returnVal = true;
    }
    return returnVal;
  }

  private static Boolean creatureInLocation(String talkCreature, Location currentLocation, Creature you, Creature[] creatures) {
    Boolean returnVal = false;
    String creaturesInRoom = currentLocation.getCreatureIds(creatures);
    if (creaturesInRoom.contains(talkCreature)) {
      returnVal = true;
    }
    return returnVal;
  }

  private static String menuOutput(String prettyExits, String exits, String stuffDescriptions, String stuffInRoom, String creaturesInRoom) {
    String returnVal = "";
    returnVal += "You can go to " + prettyExits + ". Choose one: (" + ANSI_YELLOW + exits + ANSI_RESET+ ")\n";
    if (!stuffInRoom.isEmpty()) {
      returnVal += "You can " + ANSI_CYAN + "pickup " + ANSI_RESET + stuffDescriptions + " with command: " + ANSI_CYAN + "pickup" + ANSI_RESET + " <item name> (" + ANSI_YELLOW + stuffInRoom + ANSI_RESET + ")\n";
    }
    if (!creaturesInRoom.isEmpty()) {
      returnVal += "You can " + ANSI_CYAN + "talk" + ANSI_RESET + " to " + creaturesInRoom + " with command: " + ANSI_CYAN + "talk" + ANSI_RESET + " <creature name> (" + ANSI_YELLOW + creaturesInRoom + ANSI_RESET + ")\n";
    }
    return returnVal;
  }

  private static boolean validLocation(Location[] locations, String choice) {
    // check to make sure location choice exists
    boolean found = false;
    int nextIndex = getLocation(locations, choice);
    if (nextIndex != -1) {
      found = true;
    }
    return found;
  }

  private static boolean accessibleLocation(Location currentLocation, String choice) {
    // check to make sure location choice is accessible form current location
    boolean found = false;
    for (int l = 0; l < currentLocation.exits.length; l++) {
      if (currentLocation.exits[l].equals(choice)) { found = true; }
    }
    return found;
  }

  private static int getLocation(Location[] locations, String locationId) {
    int returnVal = -1;
    for (int x = 0; x < locations.length; x++) {
      if (locationId.equals(locations[x].id)) {
        returnVal = x;
      }
    }
    return returnVal;
  }

  private static String getCreatureDialogue(String creatureId, Creature[] creatures, Item[] items) {
    String returnVal = "";
    Creature player = creatures[0];
    for(int x = 0; x < creatures.length; x++) {
      if (creatures[x].id.equals(creatureId)) {
        String dialogue = creatures[x].dialogue;
        out(dialogue);
        // give creatures giveitem to player if player has their getitem
        int playerGiveItemIndex = playerHasGetItem(player.itemIds, creatures[x].getItem);
        if (playerGiveItemIndex != -1) {
          String playerGiveItem = player.itemIds[playerGiveItemIndex];
          // determine index of creatures item to give
          int creatureGiveItemIndex = 0;
          boolean tradeOccured = false;
          for (int y = 0; y < items.length; y++) { // loop through all items
            // give creatures give item to player
            if (!tradeOccured && items[y].id == creatures[x].itemIds[creatureGiveItemIndex]) {
              player.addItem(items[y]);
              creatures[x].removeItem(items[y]);
              out("You traded your " + playerGiveItem + " for: " + items[y].id);
              tradeOccured = true;
              }
            // and take the getitem from player
            if(items[y].id == playerGiveItem) {
              creatures[x].addItem(items[y]);
              player.removeItem(items[y]);
              // out("You removed an item from your inventory");
            }
          }
          returnVal = ANSI_YELLOW + "Your inventory is: " + creatures[0].inventory() + "\n" + ANSI_RESET;
        }
      }
    }
    return returnVal;
  }

  private static int playerHasGetItem(String[] playerInventory, String creaturesGetItem) {
    int returnVal = -1;
    for(int x = 0; x < playerInventory.length; x++) {
      if (creaturesGetItem.equals(playerInventory[x])) {
        returnVal = x;
        // break;
      }
    }
    return returnVal;
  }

  public static void out(String txt) {
    System.out.println(txt);
  }

  public static void clear() {
    System.out.print("\033[H\033[2J");
  }

  public static void wait(int ms)
  {
    try
    {
      Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
      Thread.currentThread().interrupt();
    }
  }

  public static void playSound(String filename) {
    try{
      FileInputStream fs3 = new FileInputStream(filename);
      Player player3 = new Player(fs3);
      player3.play();
    } catch (Exception e){
      // catch exceptions.
    }
  }
}

class Location {

  String id;
  String name;
  String description;
  String[] exits;

  public Location(String id, String name, String description, String[] exits) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.exits = exits;
  }

  public String getDescription() { return description; }
  public String getName() { return name; }
  public String[] getExits() { return exits; }

  public String getItemIds(Item[] items) {
    String returnVal = ",";
    for (int x = 0; x < items.length; x++) {
      if(items[x].locationId.equals(this.id)) {
        returnVal += items[x].id + ", ";
      }
    }
    if (returnVal.length() == 1) {
      returnVal= "";
    } else {
      returnVal = returnVal.substring(1, returnVal.length() - 2);
    }
    return returnVal;
  }

  public String getCreatureIds(Creature[] creatures) {
    String returnVal = ",";
    for (int x = 0; x < creatures.length; x++) {
      if(creatures[x].locationId.equals(this.id)) {
        returnVal += creatures[x].id + ", ";
      }
    }
    if (returnVal.length() == 1) {
      returnVal= "";
    } else {
      returnVal = returnVal.substring(1, returnVal.length() - 2);
    }
    return returnVal;
  }

  public String getItemDescriptions(Item[] items) {
    String returnVal = ",";
    for (int x = 0; x < items.length; x++) {
      if(items[x].locationId.equals(this.id)) {
        returnVal += items[x].description + ", ";
      }
    }
    returnVal = returnVal.substring(1, returnVal.length() - 2);
    return returnVal;
  }

  public String getCreatures(Creature[] creatures) {
    String returnVal = ",";
    // start the creatures loop at 1 to avoid adding "you"
    for (int x = 1; x < creatures.length; x++) {
      if(creatures[x].locationId.equals(this.id)) {
        returnVal += creatures[x].id + ", ";
      }
    }
    if (returnVal.length() == 1) {
      returnVal= "";
    } else {
      returnVal = returnVal.substring(1, returnVal.length() - 2);
    }
    return returnVal;
  }

}

class Item {
  String id;
  String name;
  String description;
  String locationId;

  public Item(String id, String name, String description, String locationId) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.locationId = locationId;
  }

}

class Creature {
  String id;
  String name;
  String description;
  String dialogue;
  String locationId;
  String[] itemIds;
  String getItem;
  String giveItem;

  public Creature(String id, String name, String description, String dialogue, String locationId, String[] itemIds, String getItem, String giveItem) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.dialogue = dialogue;
    this.locationId = locationId;
    this.itemIds = itemIds;
    this.getItem = getItem;
    this.giveItem = giveItem;

  }

  public String pickupItem(String pickupItemId, Item[] items) {
    String returnVal = "";
    for (int x = 0; x < items.length; x++) {
      if (items[x].id.equals(pickupItemId)) {
        returnVal = addItem(items[x]);
      }
    }

    return returnVal;
  }

  public String addItem(Item item) {
    String returnVal = "";
    int currentInventoryQty = this.itemIds.length;
    String[] newItems = new String[currentInventoryQty + 1]; 
    for (int x = 0; x < this.itemIds.length; x++) {
      newItems[x] = this.itemIds[x];
    }
    newItems[currentInventoryQty] = item.id;
    this.itemIds = newItems;
    item.locationId = this.id;
    returnVal = inventory();
    return returnVal;
  }

  public String removeItem(Item item) {
    String returnVal = "";
    int currentInventoryQty = this.itemIds.length;
    String[] newItems = new String[currentInventoryQty - 1]; 
    int newItemCount = 0;
    for (int x = 0; x < this.itemIds.length; x++) {
      if (!this.itemIds[x].equals(item.id)) { // check remove item isnt this inv item
        newItems[newItemCount] = this.itemIds[x];
        newItemCount += 1;
      }
    }
    this.itemIds = newItems;
    item.locationId = this.id;
    returnVal = inventory();
    return returnVal;
  }

  public String inventory() {
    String returnVal = "";
    for (int x = 0; x < this.itemIds.length; x++) {
      returnVal += this.itemIds[x] + ", ";
    }
    if (!returnVal.equals(", ") && !returnVal.equals("")) {
      returnVal = returnVal.substring(1, returnVal.length() - 2);
    }
    return returnVal;
  }

}



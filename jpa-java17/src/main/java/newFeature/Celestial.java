package newFeature;

public sealed interface Celestial
        permits Comet, Planet, Star, SpaceShip {  }

final class Planet implements Celestial {  }
final class Star   implements Celestial {  }
non-sealed class Comet implements Celestial {  }
final class SpaceShip  implements Celestial {  }

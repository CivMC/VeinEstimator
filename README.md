# VeinEstimator

Arguments don't require any flags, they're in order

Example: java -jar VeinEstimator.jar 1 1 0.4 3 -0.2 10 10 10 80 1000 1 16

| Name          | Type   | Description                                                                                                                       |
|---------------|--------|-----------------------------------------------------------------------------------------------------------------------------------|
| heightSeed    | long   | seed for the height noise generator                                                                                               |
| densitySeed   | long   | seed for the density noise generator                                                                                              |
| density       | double | density of ore, should be between 0 and 1                                                                                         |
| maxSpan       | double | max thickness of the vein                                                                                                         |
| densityBonus  | double | between -1 and 1, smaller number creates gaps between veins large number makes them more connected. 0 looks kind of like mc caves |
| areaHeight    | double | average height veins spawn at                                                                                                     |
| areaSpan      | double | max random difference from the average                                                                                            |
| heightLength  | double | distance between height slope change                                                                                              |
| densityLength | double | distance between density slope change (large number = wide vein, small number = compact vein)   |
| radius        | integer | the radius of the world |
| minY          | integer | minimum y ores can spawn |
| maxY          | integer | maximum y ores can spawn
| drawImage     | boolean | whether or not the program should visualize the vein in an image (default true, not required)|

Color shading added by Programmerdan June 2018: 
The more "green" the greater the peak density in that area. The more
blue, the closer to the yMin of the vein's "center" is at that x,z --
the more purple, the closer to the yMax of the vein's "center".
Basically, the closer you are to yMin/yMax, the stronger you will
observe the greeness while mining.

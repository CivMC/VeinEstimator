# VeinEstimator

Arguments don't require any flags, they're in order

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

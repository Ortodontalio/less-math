<img src="src/main/resources/assets/logo.png" width=65%/><br>
<img alt="GitHub" src="https://img.shields.io/badge/dynamic/json?logo=github&label=GitHub+Followers&labelColor=545454&color=B200FF&query=%24.data.totalSubs&url=https%3A%2F%2Fapi.spencerwoo.com%2Fsubstats%2F%3Fsource%3Dgithub%26queryKey%3Dortodontalio&longCache=true"/>
<img src="https://img.shields.io/github/stars/ortodontalio?label=Stars&color=B200FF" alt="stars">
<img src="https://custom-icon-badges.demolab.com/github/license/ortodontalio/less-math?logo=law&color=B200FF" alt="watching_count" />
<img src="https://custom-icon-badges.herokuapp.com/github/last-commit/ortodontalio/less-math?logo=history&logoColor=white&color=B200FF" alt="watching_count" />
<img src="https://custom-icon-badges.demolab.com/github/v/tag/Ortodontalio/less-math?logo=tag&logoColor=white&color=B200FF" alt="tags"/><br>
[![Typing SVG](https://readme-typing-svg.demolab.com?font=Fjalla+One&size=22&pause=1000&color=AA00F7&background=FFFFFF00&center=true&vCenter=true&multiline=true&width=535&height=70&lines=LESS+-+MATH;Small+mathematical+library)](https://git.io/typing-svg)
## Preface
> _This library is a product of my inspiration. As a student of the Faculty of Mathematics,
I have repeatedly created small projects for various mathematical problems, which was the reason for my decision
to create a library - repository of mathematical formulas, functions and small tasks for creating more complex
mathematical models, solving more global problems. My main goal is to implement as extensive and flexible a mathematical
library as possible._

## Main concepts
This library is divided into packages, each of which represents a specific section of mathematics. At the moment,
the following sections and models are fully or partially implemented:
* Geometry:
  * Plane (2D):
    * Point;
    * Line;
    * Segment;
    * Triangle.

In this library, the logic is implemented in such a way that more complex objects can be obtained from more elementary
objects, for example, a segment can be obtained from two points, a line from a segment, a triangle from three segments,
and so on.

## Implemented mathematical logic
This section provides a list of available mathematical functions in this library.
### Geometry
This section provides a list of functions that relate to geometric operations.
#### [Point (2D)](src/main/java/com/ortodontalio/lessmath/geometry/plane/model/Point2D.java)
* Determine whether a point is the center of a coordinate system;
* Determine whether a point lies on the OX axis;
* Determine whether a point lies on the OY axis;
* Determine whether the points lie on the same line;
* Determine whether the points lie on the same sides;
* Determine the distance from the point to the specified line.
#### [Line (2D)](src/main/java/com/ortodontalio/lessmath/geometry/plane/model/Line2D.java)
* Determine whether a line includes some point;
* Determine whether a line is parallel to the OX axis;
* Determine whether a line is parallel to the OY axis;
* Determine whether a line passes through the center of coordinates;
* Convert the equation of a line to an equation resolved with respect to the ordinate (with an angular coefficient);
* Convert the equation of a line with angular coefficient to an equation in the general equation;
* Shift the center of coordinates and calculate the new equation of a line;
* Rotate the coordinate axes and calculate the new equation of a line;
* Determine whether the lines are parallel;
* Determine whether the lines are perpendicular;
* Determine the intersection point of two lines;
* Determine the angle (in degrees) between two lines;
* Determine a new line having the specified angle with the line;
* Determine a new line passing through the specified point parallel to the line;
* Determine a new line passing through the specified point perpendicular to the line.
#### [Segment (2D)](src/main/java/com/ortodontalio/lessmath/geometry/plane/model/Segment2D.java)
* Determine the point, that divides the segment in the specified ratio.
#### [Triangle](src/main/java/com/ortodontalio/lessmath/geometry/plane/model/Triangle2D.java)
* Get a segment-median that is drawn from the vertex of the triangle to the opposite side;
* Determine that the triangle is rectangular;
* Determine that the triangle is isosceles;
* Determine that the triangle is equilateral.
## License
This library is licensed by **Apache 2.0**. Please read the license agreement before using the source code of this
project.
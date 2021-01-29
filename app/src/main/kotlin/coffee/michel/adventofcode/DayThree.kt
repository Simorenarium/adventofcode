package coffee.michel.adventofcode

class DayThree {

    fun solve(input: List<String>): Int {
        var sum = 0
        var y=0
        var x=0

        while(y < input.size) {
            if(hasTree(x, y, input))
                sum += 1
        }

        return sum
    }

    private fun hasTree(x: Int, y: Int, input: List<String>): Boolean {
        if(y > 0) {
            
        }
    }

}
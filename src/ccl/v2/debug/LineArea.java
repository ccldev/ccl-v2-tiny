package ccl.v2.debug;

public class LineArea {

	private int startLine;
	private int lineCount;
	private int endLine;

	public LineArea(int startLine, int newLineCount) {
		this.startLine = startLine;
		this.lineCount = newLineCount + 1;
		this.endLine = startLine + newLineCount;
	}

	public int getStartLine() {
		return startLine;
	}

	public int getLineCount() {
		return lineCount;
	}

	public int getEndLine() {
		return endLine;
	}

	@Override
	public String toString() {
		return "LineArea [startLine=" + startLine + ", lineCount=" + lineCount
				+ ", endLine=" + endLine + "]";
	}

}

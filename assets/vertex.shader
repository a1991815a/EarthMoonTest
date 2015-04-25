uniform mat4 uMVPMatrix;

attribute vec3 aPosition;

void main(){
	gl_Position = uMVPMatrix * vec4(aPosition, 1);
}
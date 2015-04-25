uniform mat4 uMVPMatrix;

uniform vec3 aLightPosition;//散射光位置
uniform vec3 aEyePosition;//眼镜位置
uniform mat4 uMMatrix;//模型矩阵

attribute vec3 aPosition;	//位置
attribute vec3 aNormal;		//法向量

varying vec3 vNormal;
varying vec3 vPosition;
void main(){
	gl_Position = uMVPMatrix * vec4(aPosition, 1);
	vNormal = aNormal;
	vPosition = aPosition;
}
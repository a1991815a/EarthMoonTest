uniform mat4 uMVPMatrix;

uniform vec3 aLightPosition;//散射光位置
uniform vec3 aEyePosition;//眼镜位置
uniform mat4 uMMatrix;//模型矩阵

attribute vec3 aPosition;	//位置
attribute vec3 aNormal;		//法向量
attribute vec2 aTexCoord;	//纹理坐标

varying vec3 vNormal;
varying vec3 vPosition;
varying vec2 vTexCoord;	//纹理坐标

void main(){
	gl_Position = uMVPMatrix * vec4(aPosition, 1);
	vNormal = aNormal;
	vPosition = aPosition;
	vTexCoord = aTexCoord;
}
precision mediump float;

uniform vec3 aLightPosition;//光位置
uniform vec3 aEyePosition;//眼镜位置
uniform mat4 uMMatrix;//模型矩阵

varying vec3 vNormal;//法向量
varying vec3 vPosition;//顶点坐标

void main(){
	vec4 colors = vec4(0,1,1,1);
	float roughness = 50.0;
	vec4 environmentIntens = vec4(0.15,0.15,0.15,1);
	vec4 spIntens = vec4(0.7,0.7,0.7,1);
	vec4 scIntens = vec4(0.8,0.8,0.8,1);
	
	vec3 normal = normalize(uMMatrix * vec4(vNormal + vPosition,1)).xyz - normalize(uMMatrix * vec4(vPosition, 1)).xyz;
	normal = normalize(normal);
	
	vec3 vLight = normalize(aLightPosition - vPosition);
	vec3 vEye = normalize(aEyePosition - vPosition);
	vec3 vHalf = normalize(vLight + vEye);
	
	spIntens = spIntens * max(dot(normal,vLight), 0.0);
	scIntens = scIntens * max(pow(dot(normal,vHalf), roughness), 0.0);
	
	
	gl_FragColor = colors*environmentIntens + colors*spIntens + colors*scIntens;
}
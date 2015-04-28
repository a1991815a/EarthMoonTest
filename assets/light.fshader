precision mediump float;

uniform vec3 aLightPosition;//光位置
uniform vec3 aEyePosition;//眼镜位置
uniform mat4 uMMatrix;//模型矩阵

uniform sampler2D uTexture0;//0号纹理
uniform sampler2D uTexture1;//0号纹理

varying vec3 vNormal;//法向量
varying vec3 vPosition;//顶点坐标
varying vec2 vTexCoord;	//纹理坐标

void main(){
	

	vec4 colorsDay = texture2D(uTexture0, vTexCoord);
	vec4 colorsNight = texture2D(uTexture1, vTexCoord);
	
	float roughness = 40.0;
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
	
	colorsDay = colorsDay*environmentIntens + colorsDay*spIntens + colorsDay*scIntens;
	colorsNight = colorsNight*environmentIntens + colorsNight*spIntens + colorsNight*scIntens;
	
	if(spIntens.x > 0.21){
		gl_FragColor = colorsDay;
	}else if(spIntens.x < 0.05){
		gl_FragColor = colorsNight;
	}else{
		float tt = (spIntens.x - 0.05)/0.16;
		gl_FragColor = tt * colorsDay + (1.0 - tt) * colorsNight;
	}
}
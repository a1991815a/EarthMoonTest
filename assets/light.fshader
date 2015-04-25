precision mediump float;

uniform vec3 aLightPosition;//��λ��
uniform vec3 aEyePosition;//�۾�λ��
uniform mat4 uMMatrix;//ģ�;���

varying vec3 vNormal;//������
varying vec3 vPosition;//��������

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
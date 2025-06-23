<template>
  <div class="wallpaper-container">
    <canvas ref="canvas" class="interactive-wallpaper"></canvas>
  </div>
</template>

<script>
export default {
  name: 'InteractiveWallpaper',
  mounted() {
    this.initCanvas();
  },
  methods: {
    initCanvas() {
      const canvas = this.$refs.canvas;
      const ctx = canvas.getContext('2d');

      canvas.width = window.innerWidth;
      canvas.height = window.innerHeight;

      const particles = [];

      class Particle {
        constructor(x, y) {
          this.x = x;
          this.y = y;
          this.size = Math.random() * 5 + 1;
          this.speedX = Math.random() * 3 - 1.5;
          this.speedY = Math.random() * 3 - 1.5;
        }
        update() {
          this.x += this.speedX;
          this.y += this.speedY;
        }
        draw() {
          ctx.fillStyle = 'rgba(255, 255, 255, 0.8)';
          ctx.beginPath();
          ctx.arc(this.x, this.y, this.size, 0, Math.PI * 2);
          ctx.closePath();
          ctx.fill();
        }
      }

      function handleParticles() {
        for (let i = 0; i < particles.length; i++) {
          particles[i].update();
          particles[i].draw();
        }
      }

      canvas.addEventListener('mousemove', (event) => {
        particles.push(new Particle(event.x, event.y));
        if (particles.length > 100) {
          particles.shift();
        }
      });

      function animate() {
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        handleParticles();
        requestAnimationFrame(animate);
      }

      animate();
    },
  },
};
</script>

<style scoped>
.wallpaper-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  /* background-color: rgb(67, 157, 236); 设置背景颜色为蓝色 */
  background-image: url('~@/assets/images/login.jpg'); /* 设置背景图片 */
  background-size: cover; /* 使背景图片覆盖整个容器 */
  overflow: hidden;
}

.interactive-wallpaper {
  display: block;
  width: 100%;
  height: 100%;
}
</style>

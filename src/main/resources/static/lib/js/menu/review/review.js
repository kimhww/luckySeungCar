function saveScore() {
      const fullScore = 10;
      const userScore = document.getElementById("userScore").value || 0;
      const reviewScore = userScore + "/" + fullScore;
      alert("평점을 저장하였습니다.");

      document.getElementById("display").innerHTML =
        '<div class="rating-box">' +
          '<span class="icon">🛠️</span>' +
          '<span>평점 ' + reviewScore + '</span>' +
        '</div>';

      document.getElementById("display").style.display = "block";
    }
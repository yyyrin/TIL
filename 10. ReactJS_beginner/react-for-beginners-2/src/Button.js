import PropTypes from "prop-types";
import styles from "./Button.module.css";

function Button({ text }) {
  return <button className={styles.btn}>{text}</button>;
}

// prop type 확인하기
// propTypes 소문자로 시작한다!!!
Button.propTypes = {
  text: PropTypes.string.isRequired,
};

// App.js에서 Button 가져오기 위해서
export default Button;

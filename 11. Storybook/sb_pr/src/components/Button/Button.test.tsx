import {
  RedButton,
  BlueButton,
} from "./Button.stories"
import {render, screen} from "@testing-library/react"

const mainColor = "brown";

test("should render RedButton", () => {
  render(<RedButton {...RedButton.args} />)
  expect(screen.getByRole("button")).toHaveTextContent(/Red/i)
  expect(screen.getByRole("button")).toHaveStyle("backgroundColor: red")
});

test("should render BlueButton", () => {
  render(<BlueButton {...BlueButton.args} />)
  expect(screen.getByRole("button")).toHaveTextContent(/Blue/i)
  expect(screen.getByRole("button")).toHaveStyle("backgroundColor: blue")
});
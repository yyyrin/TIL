import Button, { ButtonProps} from '../components/Button';
import {Meta, Story} from "@storybook/react";

export default {
  title: 'Button',
  component: Button,
  argTypes: {clickHandler: {action: "clicked"}},
} as Meta;

const Template: Story<ButtonProps> = (args) => <Button {...args} />;

export const Red = Template.bind({});
Red.args = {
  label: 'Red',
  backgroundColor: "red",
  size: "md",
  color: "white",
};

export const Blue = Template.bind({});
Blue.args = {
  label: 'Blue',
  backgroundColor: "blue",
  size: "md",
  color: "white",
};
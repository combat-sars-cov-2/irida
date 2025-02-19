import React from "react";
import { useDispatch } from "react-redux";
import { Avatar, Button, List, Tooltip } from "antd";
import { green6 } from "../../../styles/colors";
import { IconLocked, IconUnlocked } from "../../../components/icons/Icons";
import { SampleDetailViewer } from "../../../components/samples/SampleDetailViewer";

/**
 * Render a list item for the samples to be shared with another project.
 * @param {array} sample - sample to display
 * @param {object} style - style to apply to the list item
 * @returns
 */
export default function ShareSamplesListItem({ sample, style }) {
  const dispatch = useDispatch();

  return (
    <List.Item
      style={{ ...style }}
      className="t-share-sample"
      actions={[
        <Button
          key="remove"
          type="link"
          onClick={() => dispatch(removeSample(sample.id))}
        >
          {i18n("ShareSamples.remove")}
        </Button>,
      ]}
    >
      <List.Item.Meta
        avatar={
          sample.owner ? (
            <Tooltip
              title={i18n("ShareSamples.avatar.unlocked")}
              placement="right"
              color={green6}
            >
              <Avatar
                style={{ backgroundColor: green6 }}
                className="t-unlocked-sample"
                size="small"
                icon={<IconUnlocked />}
              />
            </Tooltip>
          ) : (
            <Tooltip title={i18n("ShareSamples.avatar.locked")}>
              <Avatar
                className="t-locked-sample"
                size="small"
                icon={<IconLocked />}
              />
            </Tooltip>
          )
        }
        title={
          <SampleDetailViewer sampleId={sample.id}>
            <Button>{sample.name}</Button>
          </SampleDetailViewer>
        }
      />
    </List.Item>
  );
}
